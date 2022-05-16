import glob
import os

import matplotlib.pyplot as plt
import numpy as np
from PIL import Image, ImageEnhance

from Model.detect_light import find_tfl_lights_points


def darken(img):
    enhancer = ImageEnhance.Brightness(img)
    im_output = enhancer.enhance(0.35)
    return im_output


def crop(img_path, y, x, color):
    print(x, y)
    startx = max(0, x - 41)
    starty = max(0, y - color)
    startx = 2048 - 81 if (startx + 81) > 2048 else startx
    starty = 1024 - 81 if (starty + 81) > 1024 else starty
    image = load_image(img_path)
    return np.array(image[starty:min(starty + 81, 1024), startx:min(startx + 81, 2048)])


def load_image(image_path):
    image = np.array(Image.open(image_path))
    return image


def write_to_bin(image, label):
    print(image.shape, label.shape)
    with open('data.bin', 'ab') as data_bin:
        data_bin.write(image.tobytes())
    with open('labels.bin', 'ab') as labels_bin:
        labels_bin.write(label.to_bytes(1, 'little'))


def is_tfl(img_pass, point):
    return True


def create_data_set(folder_path, flist=None):
    image_list = glob.glob(os.path.join(folder_path, '*_leftImg8bit.png'))
    for img_path in image_list:
        points = find_tfl_lights_points(img_path)
        for point in points:
            crop_img = crop(img_path, point[0], point[1], point[2])

            darken_img = darken(crop_img)
            if is_tfl(img_path, point):
                label = 1
            else:
                label = 0
            write_to_bin(crop_img, label)
            write_to_bin(darken_img, label)
