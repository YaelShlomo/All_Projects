import math

import numpy as np


# from scipy import stats

def calc_TFL_dist(prev_container, curr_container, focal, pp):
    norm_prev_pts, norm_curr_pts, R, foe, tZ = prepare_3D_data(prev_container, curr_container, focal, pp)

    if abs(tZ) < 10e-6:
        print('tz = ', tZ)
    elif norm_prev_pts.size == 0:
        print('no prev points')
    elif norm_curr_pts.size == 0:
        print('no curr points')
    else:
        curr_container.corresponding_ind, curr_container.traffic_lights_3d_location, curr_container.valid = calc_3D_data(
            norm_prev_pts, norm_curr_pts, R, foe, tZ)
    return curr_container


def prepare_3D_data(prev_container, curr_container, focal, pp):
    norm_prev_pts = normalize(prev_container.traffic_light, focal, pp)
    norm_curr_pts = normalize(curr_container.traffic_light, focal, pp)
    R, foe, tZ = decompose(np.array(curr_container.EM))
    return norm_prev_pts, norm_curr_pts, R, foe, tZ


def calc_3D_data(norm_prev_pts, norm_curr_pts, R, foe, tZ):
    norm_rot_pts = rotate(norm_prev_pts, R)
    pts_3D = []
    corresponding_ind = []
    validVec = []
    for p_curr in norm_curr_pts:
        corresponding_p_ind, corresponding_p_rot = find_corresponding_points(p_curr, norm_rot_pts, foe)
        Z = calc_dist(p_curr, corresponding_p_rot, foe, tZ)
        valid = (Z > 0)
        if not valid:
            Z = 0
        validVec.append(valid)
        P = Z * np.array([p_curr[0], p_curr[1], 1])
        pts_3D.append((P[0], P[1], P[2]))
        corresponding_ind.append(corresponding_p_ind)
    return corresponding_ind, np.array(pts_3D), validVec


def normalize(pts, focal, pp):
    # transform pixels into normalized pixels using the focal length and principle point
    x = (pts[:, 0] - pp[0]) / focal
    y = (pts[:, 1] - pp[1]) / focal
    return np.column_stack((x, y, [1] * len(x)))


def unnormalize(pts, focal, pp):
    # transform normalized pixels into pixels using the focal length and principle point
    x = (pts[:, 0] * focal) + pp[0]
    y = (pts[:, 1] * focal) + pp[1]
    return np.column_stack((x, y))


def decompose(EM):
    # extract R, foe and tZ from the Ego Motion
    R = EM[:3, :3]
    t = EM[:, -1]
    foe = ((t[0] / t[2]), (t[1] / t[2]))
    return R, foe, t[2]


def rotate(pts, R):
    # rotate the points - pts using R
    X, Y = [], []
    for i in range(len(pts)):
        vector = R.dot(pts[i])
        X.append(vector[0] / vector[2])
        Y.append(vector[1] / vector[2])
    return np.column_stack((X, Y))


def calc_dist_foe(point, p, foe):
    ex = foe[0]
    ey = foe[1]
    x_gal = p[0]
    y_gal = p[1]
    m = ((ey - y_gal) / ((ex - x_gal)))
    y = m * point[0] + ((y_gal * ex) - (ey * x_gal)) / (ex - x_gal)
    d = (y - point[1]) / math.sqrt(m ** 2 + 1)
    return abs(d)


def find_corresponding_points(p, norm_pts_rot, foe):
    # compute the epipolar line between p and foe
    # run over all norm_pts_rot and find the one closest to the epipolar line
    # return the closest point and its index
    minimum = calc_dist_foe(norm_pts_rot[0], p, foe)
    index = 0
    for i in range(1, len(norm_pts_rot)):
        point = norm_pts_rot[i]
        dist = calc_dist_foe(point, p, foe)
        if dist < minimum:
            minimum = dist
            index = i
    return index, norm_pts_rot[index]


def calc_dist(p_curr, p_rot, foe, tZ):
    # calculate the distance of p_curr using x_curr, x_rot, foe_x and tZ
    # calculate the distance of p_curr using y_curr, y_rot, foe_y and tZ
    # combine the two estimations and return estimated Z
    z_x = tZ * ((foe[0] - p_rot[0]) / (p_curr[0] - p_rot[0]))
    z_y = tZ * ((foe[1] - p_rot[1]) / (p_curr[1] - p_rot[1]))

    x_dis = abs(p_curr[0] - p_rot[0])
    y_dis = abs(p_curr[1] - p_rot[1])

    return (x_dis * z_x + y_dis * z_y) / (x_dis + y_dis)
