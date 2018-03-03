#! /bin/sh

git clone https://github.com/matterport/Mask_RCNN.git
git clone https://github.com/philferriere/cocoapi.git
pip3 install git+https://github.com/philferriere/cocoapi.git#subdirectory=PythonAPI

cd Mask_RCNN

wget https://github.com/matterport/Mask_RCNN/releases/download/v2.0/mask_rcnn_coco.h5
wget https://s3.amazonaws.com/deep-spaces/camera.mp4
cp ../MaskRCNN.py .
python3 MaskRCNN.py
