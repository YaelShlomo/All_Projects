class FrameData:
    def __init__(self, pkl_data):
        self.focal = pkl_data['flx']
        self.pp = pkl_data['principle_point']
        self.EMs = [calc_EM(i + 24, pkl_data) for i in range(1, 6)]


def calc_EM(curr_id, pkl_data):
    return pkl_data['egomotion_' + str(curr_id - 1) + '-' + str(curr_id)]
