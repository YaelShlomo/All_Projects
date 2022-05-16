from os.path import join

import matplotlib as plt
import numpy as np
from sympy.utilities.tests.test_lambdify import tensorflow


def load_tfl_data(data_dir, crop_shape=(81, 81)):
    images = np.memmap(join(data_dir, 'data.bin'), mode='r', dtype=np.uint8).reshape([-1] + list(crop_shape) + [3])
    labels = np.memmap(join(data_dir, 'labels.bin'), mode='r', dtype=np.uint8)
    return {'images': images, 'labels': labels}


def viz_my_data(images, labels, predictions=None, num=(5, 5), labels2name={0: 'No TFL', 1: 'Yes TFL'}):
    print(images.shape, labels.shape)
    assert images.shape[0] == labels.shape[0]
    assert predictions is None or predictions.shape[0] == images.shape[0]
    h = 5
    n = num[0] * num[1]
    ax = plt.subplots(num[0], num[1], figsize=(h * num[0], h * num[1]), gridspec_kw={'wspace': 0.05}, squeeze=False,
                      sharex=True, sharey=True)[1]  # .flatten()
    idxs = np.random.randint(0, images.shape[0], n)
    for i, idx in enumerate(idxs):
        ax.flatten()[i].imshow(images[idx])
        title = labels2name[labels[idx]]
        if predictions is not None: title += ' Prediction: {:.2f}'.format(predictions[idx])
        ax.flatten()[i].set_title(title)


# root = './'  #this is the root for your val and train datasets
data_dir = 'C:/Users/mby/Desktop/bootcamp/mobileye/part2_'
data_sets = {
    'val': load_tfl_data(join(data_dir, 'val')),
    'train': load_tfl_data(join(data_dir, 'train')),
}
for k, v in data_sets.items():
    print('{} :  {} 0/1 split {:.1f} %'.format(k, v['images'].shape, np.mean(v['labels'] == 1) * 100))

viz_my_data(num=(6, 6), **data_sets['val'])

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Input, Dense, Flatten, Activation, MaxPooling2D, BatchNormalization, Activation, \
    Conv2D


def tfl_model():
    input_shape = (81, 81, 3)

    model = Sequential()

    def conv_bn_relu(filters, **conv_kw):
        model.add(Conv2D(filters, use_bias=False, kernel_initializer='he_normal', **conv_kw))
        model.add(BatchNormalization())
        model.add(Activation('relu'))

    def dense_bn_relu(units):
        model.add(Dense(units, use_bias=False, kernel_initializer='he_normal'))
        model.add(BatchNormalization())
        model.add(Activation('relu'))

    def spatial_layer(count, filters):
        for i in range(count):
            conv_bn_relu(filters, kernel_size=(3, 3))
        conv_bn_relu(filters, kernel_size=(3, 3), strides=(2, 2))

    conv_bn_relu(32, kernel_size=(3, 3), input_shape=input_shape)
    spatial_layer(1, 32)
    spatial_layer(2, 64)
    spatial_layer(2, 96)

    model.add(Flatten())
    model.add(Dense(2, activation='softmax'))
    return model


cnn_model = tfl_model()
cnn_model.summary()

from tensorflow.keras.losses import sparse_categorical_crossentropy, BinaryCrossentropy

# from tensorflow.keras.optimizers import Adam

data_dir = 'C:/Users/mby/Desktop/bootcamp/mobileye/part2_'
data_set = {
    'val': load_tfl_data(join(data_dir, 'val')),
    'train': load_tfl_data(join(data_dir, 'train')),
}
# prepare our model
cnn_model = tfl_model()
cnn_model.compile(optimizer=tensorflow.keras.Adam(), loss=sparse_categorical_crossentropy, metrics=['accuracy'])
train, val = data_set['train'], data_set['val']
history = cnn_model.fit(train['images'], train['labels'], validation_data=(val['images'], val['labels']),
                        epochs=10)  # 8\n"

# train it, the model uses the 'train' dataset for learning. We evaluate the \"goodness\" of the model, by predicting the label of the images in the val dataset.

# m.compile(optimizer=Adam(learning_rate=0.0003),loss = keras.losses.sparse_categorical_crossentropy(from_logits=True),metrics=['accuracy'])


cnn_model.save("model.h5")

import seaborn

predictions = cnn_model.predict(val['images'])
seaborn.distplot(predictions[:, 0])

predicted_label = np.argmax(predictions, axis=-1)
print('accuracy: ', np.mean(predicted_label == val['labels']))
