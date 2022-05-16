from PIL import Image
import numpy as np


def crop(img, x, y, color):
    start_x = max(0, x - 41)
    start_y = max(0, y - color)
    start_x = 2048 - 81 if (start_x + 81) > 2048 else start_x
    start_y = 1024 - 81 if (start_y + 81) > 1024 else start_y
    image = load_image(img)

    return np.array(image[start_y:min(start_y + 81, 1024), start_x:min(start_x + 81, 2048)])


def load_image(image_path):
    image = np.array(Image.open(image_path))
    return image


def crop_all_images(img, candidates):
    croped_imgs = []
    for c in candidates:
        croped_imgs.append(crop(img, c[0], c[1], c[2]))

    return np.array(croped_imgs)
