from PIL import Image
from tensorflow.keras.models import load_model
import matplotlib.pyplot as plt
import numpy as np

from Model import SFM_standAlone, predict_tfl, SFM, detect_light
from Data.frame_data import FrameData
from View.output import Output


class TFLManager(object):
    def __init__(self, pkl_data):
        self.model = load_model("./Data/model.h5")
        self.frames_data = FrameData(pkl_data)

    def run(self, frame, prev_container, index):
        output = Output()

        curr_container = SFM_standAlone.FrameContainer(frame)
        curr_container.traffic_light = self.detect_lights(curr_container.img_path, output.light_src)

        curr_container.traffic_light = self.predict_tfls(curr_container.img_path, curr_container.traffic_light, output.tfl)
        if index:
            curr_container.EM = self.frames_data.EMs[index - 1]
            self.calc_distances(curr_container, prev_container, output.distances)

        output.show_result()

        return curr_container

    def detect_lights(self, img_path, fig):
        print("Detecting lights")
        img = plt.imread(img_path).copy()
        candidates = detect_light.find_tfl_lights_points(img_path)
        detect_light.visualize(img, candidates, fig, "Light Detection")

        return candidates

    def predict_tfls(self, img_path, light_candidates, fig):
        print("Predict TFL")

        # img = plt.imread(img_path).copy()
        img = np.array(Image.open(img_path))
        images = predict_tfl.crop_all_images(img_path, light_candidates)
        images *= 255

        predictions = self.model.predict(images)

        assert len(predictions) <= len(light_candidates)

        traffic_light_candidates = [candidate for index, candidate in enumerate(light_candidates) if
                            predictions[index, 1] > 0.99]

        # detect_light.visualize(img, light_candidates, fig, "TFL Prediction")


        traffic_light_candidates = []
        for point in light_candidates:
            if point[2] == 25:
                traffic_light_candidates.append(point)

        detect_light.visualize(img, traffic_light_candidates, fig, "TFL Prediction")

        return traffic_light_candidates

    def calc_distances(self, curr_container, prev_container, fig):
        print("Calculating SFM")
        curr_container.traffic_light = np.array(curr_container.traffic_light)
        prev_container.traffic_light = np.array(prev_container.traffic_light)
        curr_container = SFM.calc_TFL_dist(prev_container, curr_container, self.frames_data.focal, self.frames_data.pp)
        SFM_standAlone.visualize(prev_container, curr_container, self.frames_data.focal, self.frames_data.pp, fig)
