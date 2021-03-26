# coding=utf-8
# 导入python包
from imutils.video import VideoStream
import numpy as np
import imutils
import argparse
import time
import cv2

# 定义简单的图片检测函数，这个思路就是单张图片的检测思路
def detect(image):
	gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

	ddepth = cv2.cv.CV_32F if imutils.is_cv2() else cv2.CV_32F
	gradX = cv2.Sobel(gray, ddepth=ddepth, dx=1, dy=0, ksize=-1)
	gradY = cv2.Sobel(gray, ddepth=ddepth, dx=0, dy=1, ksize=-1)

	gradient = cv2.subtract(gradX, gradY)
	gradient = cv2.convertScaleAbs(gradient)

	blurred = cv2.blur(gradient, (9, 9))
	(_, thresh) = cv2.threshold(blurred, 225, 255, cv2.THRESH_BINARY)

	kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (21, 7))
	closed = cv2.morphologyEx(thresh, cv2.MORPH_CLOSE, kernel)

	closed = cv2.erode(closed, None, iterations=4)
	closed = cv2.dilate(closed, None, iterations=4)

	cnts = cv2.findContours(closed.copy(), cv2.RETR_EXTERNAL,
		cv2.CHAIN_APPROX_SIMPLE)
	cnts = imutils.grab_contours(cnts)

	if len(cnts) == 0:
		return None

	c = sorted(cnts, key=cv2.contourArea, reverse=True)[0]
	rect = cv2.minAreaRect(c)
	box = cv2.cv.BoxPoints(rect) if imutils.is_cv2() else cv2.boxPoints(rect)
	box = np.int0(box)

	return box

# 构建并解析参数
ap = argparse.ArgumentParser()
ap.add_argument("-v", "--video", help="path to the (optional) video file")
args = vars(ap.parse_args())

# 如果视频不存在则进行视频捕获
if not args.get("video", False):
	vs = VideoStream(src=0).start()
	time.sleep(2.0)
# 否则装载视频
else:
	vs = cv2.VideoCapture(args["video"])

# 循环处理每一帧
while True:
	# 获取当前帧
	frame = vs.read()
	frame = frame[1] if args.get("video", False) else frame
 
	# 判断是否达到视频的最后一帧
	if frame is None:
		break

	# 进行单张图片检测
	box = detect(frame)

	# 如果发现了条形码则绘制结果
	if box is not None:
		cv2.drawContours(frame, [box], -1, (0, 255, 0), 2)

	# 显示当前帧的结果
	cv2.imshow("Frame", frame)
	key = cv2.waitKey(1) & 0xFF

	# 当用户按下q键时退出整个循环
	if key == ord("q"):
		break

# 停止视频捕获
if not args.get("video", False):
	vs.stop()
# 否则释放内存
else:
	vs.release()

# 关闭所有的窗口
cv2.destroyAllWindows()