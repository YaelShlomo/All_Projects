import numpy as np
import skimage
from scipy import signal as sg
from scipy.ndimage.filters import maximum_filter
from PIL import Image


def get_light_positions(c_image: np.ndarray, colorful_image):
    """
    the main function that finds the traffic lights.
    creates a convolution of the high pass filter with the grayscale image.
    creates another convolution on the return image of the first convolution.
    then searches for the high value colors that have maximum values,
    then checks in the original image if the light is green or red based on the RGB values,
    and add them to the right coordinates.
    :param c_image: the grayscale image created.
    :param colorful_image: the same image in full color
    :return: the coordinates of the red and green traffic lights(blue if no know the color).
    """
    red_x, red_y = [], []
    green_x, green_y = [], []
    blue_x, blue_y = [], []

    c_image = c_image.astype(np.float)

    high_pass_filter = create_kernel_high_pass()
    high_pass_image = sg.convolve(c_image, high_pass_filter, "same")

    kernel_to_big_trl = create_circle_kernel(3.5, 3)
    convolve_image_1 = sg.convolve(high_pass_image, kernel_to_big_trl, 'same')

    kernel_to_small_trl = create_circle_kernel(2.5, 2)
    convolve_image_2 = sg.convolve(high_pass_image, kernel_to_small_trl, 'same')

    max_image_1 = maximum_filter(convolve_image_1, size=(15, 15)) == convolve_image_1
    max_image_2 = maximum_filter(convolve_image_2, size=(15, 15)) == convolve_image_2

    marge_matrix = np.logical_or(max_image_1, max_image_2)
    max_x, max_y = np.where(marge_matrix)  # get the true points from the maxmimum filter

    for i in range(len(max_x)):
        x_idx, y_idx = max_x[i], max_y[i]
        if convolve_image_1[x_idx][y_idx] > 900 or convolve_image_2[x_idx][y_idx] > 900:
            if 30 < x_idx < 1000 and 4 < y_idx < 2044:  # only if the point not in the end of the picture.
                if colorful_image[x_idx][y_idx][0] > colorful_image[x_idx][y_idx][1] \
                        or colorful_image[x_idx - 4][y_idx][0] > colorful_image[x_idx - 4][y_idx][1] \
                        or colorful_image[x_idx + 4][y_idx][0] > colorful_image[x_idx + 4][y_idx][1] \
                        or colorful_image[x_idx][y_idx + 4][0] > colorful_image[x_idx][y_idx + 4][1] \
                        or colorful_image[x_idx][y_idx - 4][0] > colorful_image[x_idx][y_idx - 4][1]:
                    red_y += [x_idx]
                    red_x += [y_idx]
                elif colorful_image[x_idx][y_idx][0] < colorful_image[x_idx][y_idx][1] \
                        or colorful_image[x_idx - 4][y_idx][0] < colorful_image[x_idx - 4][y_idx][1] \
                        or colorful_image[x_idx + 4][y_idx][0] < colorful_image[x_idx + 4][y_idx][1] \
                        or colorful_image[x_idx][y_idx + 4][0] < colorful_image[x_idx][y_idx + 4][1] \
                        or colorful_image[x_idx][y_idx - 4][0] < colorful_image[x_idx][y_idx - 4][1]:
                    green_y += [x_idx]
                    green_x += [y_idx]
                else:
                    blue_y += [x_idx]
                    blue_x += [y_idx]

    return red_x, red_y, green_x, green_y, blue_x, blue_y


def create_kernel_high_pass():
    """
    function to create the high pass filter kernel,
    create a 31x31 matrix that sums up to 0.
    :return: the kernel created
    """
    arr = np.full((31, 31), -1 / 961)
    arr[16][16] = 960 / 961
    return arr


def create_circle_kernel(stroke, radius):
    """
    function to create the second kernel.
    creates a 17x17 matrix of 0's with a circle shape border of 1's.
    :return: the kernel created
    """
    arr = np.zeros((16, 16))
    # Create an outer and inner circle. Then subtract the inner from the outer.
    inner_radius = radius - (stroke // 2) + (stroke % 2) - 1
    outer_radius = radius + ((stroke + 1) // 2)
    ri, ci = skimage.draw.circle_perimeter(8, 8, radius=int(inner_radius), shape=arr.shape)
    ro, co = skimage.draw.circle_perimeter(8, 8, radius=int(outer_radius), shape=arr.shape)
    arr[ro, co] = 1
    arr[ri, ci] = 0

    return arr


def find_tfl_lights_points(image_path):
    """
    Function that runs the program itself,
    changes the image to grayscale colors,
    when wanting to test an image, creates an object from the json file whose path is given.
    sends the image to the function that finds the traffic lights and then shows it.
    :param image_path: path to the image currently running on.
    :param json_path: path to the json file if interested in to test the image.
    :param fig_num: the size wanted for the display of the image
    """
    color_image = np.array(Image.open(image_path))
    image = np.array(Image.open(image_path).convert('P'))

    red_x, red_y, green_x, green_y, blue_x, blue_y = get_light_positions(image, color_image)
    points = []
    for i in range(len(red_x)):
        points.append((red_x[i], red_y[i], 25))
    for i in range(len(green_x)):
        points.append((green_x[i], green_y[i], 55))
    for i in range(len(blue_x)):
        points.append((blue_x[i], blue_y[i], 40))

    return points


def visualize(image, candidates, fig, title):
    fig.set_title(title)
    fig.imshow(image)
    fig.set_xticks([])
    fig.set_yticks([])
    x_red, y_red = [], []
    x_green, y_green = [], []
    x_blue, y_blue = [], []
    for x in candidates:
        if x[2] == 25:
            x_red.append(x[0])
            y_red.append(x[1])
        elif x[2] == 55:
            x_green.append(x[0])
            y_green.append(x[1])
        else:
            x_blue.append(x[0])
            y_blue.append(x[1])

    # x_red, y_red = [(x[0], x[1]) for x in candidates if x[2] == 25]
    # x_green, y_green = [(x[0], x[1]) for x in candidates if x[2] == 55]
    # x_blue, y_blue = [(x[0], x[1]) for x in candidates if x[2] == 40]

    fig.plot(x_red, y_red, 'r.')
    print(x_green)
    fig.plot(x_green, y_green, 'g.')
    fig.plot(x_blue, y_blue, 'b.')