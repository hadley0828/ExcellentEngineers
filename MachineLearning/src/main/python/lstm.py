# -*- coding: utf-8 -*-
"""
Simple example using LSTM recurrent neural network to classify IMDB
sentiment dataset.

References:
    - Long Short Term Memory, Sepp Hochreiter & Jurgen Schmidhuber, Neural
    Computation 9(8): 1735-1780, 1997.
    - Andrew L. Maas, Raymond E. Daly, Peter T. Pham, Dan Huang, Andrew Y. Ng,
    and Christopher Potts. (2011). Learning Word Vectors for Sentiment
    Analysis. The 49th Annual Meeting of the Association for Computational
    Linguistics (ACL 2011).

Links:
    - http://deeplearning.cs.cmu.edu/pdfs/Hochreiter97_lstm.pdf
    - http://ai.stanford.edu/~amaas/data/sentiment/

"""
from __future__ import division, print_function, absolute_import

import socket

import tensorflow as tf
import tflearn
from tflearn.data_utils import to_categorical, pad_sequences
from tflearn.datasets import imdb
import six.moves.cPickle as pickle
import data_utils

file_train_X = 'trainX'
file_train_Y = 'trainY'
vocabulary_Path = ''
data_Path = ''
comment_train_set_Path = ''
comment_set_Path = ''
token_comment_set = ''

PKL_PATH = ''
MODEL_PATH = ''


def data_process():
    #data_utils.create_vocabulary(vocabulary_path=vocabulary_Path, data_path=comment_train_set_Path,
    #                             max_vocabulary_size=40000, tokenizer=False)
    #data_utils.data_to_token_ids(data_path=comment_set_Path, target_path=token_comment_set,
    #                             vocabulary_path=vocabulary_Path, tokenizer=False)
    # data_utils.data_to_token_ids(data_path='comment_test_set.txt',target_path='testX',vocabulary_path='vocab40000',tokenizer=False)
    #print('end')
    # Amazon Dataset loading
    train, test, _ = imdb.load_data(path=PKL_PATH, n_words=40000,
                                    valid_portion=0.1)
    trainX, trainY = train
    testX, testY = test
    #
    # with open(file_train_X,'w') as f:
    #     for strs in trainX:
    #         f.write(' '.join(str(e) for e in strs)+"\n")
    # with open(file_train_Y,'w') as f:
    #     f.write(' '.join(str(e)+"\n" for e in trainY))


    # Data preprocessing
    # Sequence padding
    trainX = pad_sequences(trainX, maxlen=300, value=0.)
    testX = pad_sequences(testX, maxlen=300, value=0.)
    # Converting labels to binary vectors
    trainY = to_categorical(trainY, nb_classes=6)
    testY = to_categorical(testY, nb_classes=6)
    return trainX,trainY,testX,testY


def network_building():
    # Network building
    net = tflearn.input_data([None, 300])
    net = tflearn.embedding(net, input_dim=40000, output_dim=128)
    net = tflearn.lstm(net, 128, dropout=0.8)
    net = tflearn.fully_connected(net, 6, activation='softmax')
    net = tflearn.regression(net, optimizer='adam', learning_rate=0.001,
                             loss='categorical_crossentropy')
    return net

def train():
    # Training
    trainX,trainY,testX,testY = data_process()
    net = network_building()
    model = tflearn.DNN(net, tensorboard_verbose=0)
    model.fit(trainX, trainY, validation_set=(testX, testY), show_metric=True,
              batch_size=32)
    model.save(MODEL_PATH)

def decode():
    # Decode
    net = network_building()
    model = tflearn.DNN(net, tensorboard_verbose=0)
    model.load(MODEL_PATH)
    en_vocab, _ = data_utils.initialize_vocabulary(vocabulary_Path)
    #text = 'The paper is great. However, it comes glued and to start the roll there is quite a bit of waste. Perhaps, you can find a way to package it. Thanks'


    print('start commenting')
    HOST = ''  # Symbolic name meaning all available interfaces
    PORT = 8082  # Arbitrary non-privileged port
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((HOST, PORT))
    s.listen(1)
    conn, addr = s.accept()
    print('Connected by', addr)

    while True:
        text = conn.recv(1024)
        if not text:
            break
        print(str(text,'utf-8').rstrip())

        text = data_utils.sentence_to_token_ids(tf.compat.as_bytes(text), vocabulary=en_vocab, tokenizer=False)
        # print(text)
        datalist = []
        datalist.append(text)
        datalist = pad_sequences(datalist, maxlen=300, value=0.)
        # print(datalist)
        result = model.predict(datalist)
        # resultlabel = model.predict_label(datalist)
        print(result)
        # print(resultlabel)
        resultlist = result[0]
        maxnum = 0.0
        score = 0
        # print(resultlist)
        for i in range(len(resultlist)):
            if resultlist[i] > maxnum:
                maxnum = resultlist[i]
                score = i
        print(score)
        conn.sendall(bytes(" ".join(str(score))+'\n','utf-8'))


decode()