import pickle

from Model.TFLManager import TFLManager


class Controller:
    def __init__(self, file_path):
        pls_data = read_pls(file_path)
        self.frames = pls_data[1:]
        self.pkl_data = read_pkl(pls_data[0])
        self.tfl_manager = TFLManager(self.pkl_data)

    def run(self):
        prev_frame = None

        for index_frame, current_frame in enumerate(self.frames):
            prev_frame = self.tfl_manager.run(current_frame, prev_frame, index_frame)



def read_pls(file_path):
    with open(file_path, 'r') as file:
        return file.read().split("\n")


def read_pkl(file_path):
    with open(file_path, 'rb') as file:
        return pickle.load(file, encoding='latin1')
