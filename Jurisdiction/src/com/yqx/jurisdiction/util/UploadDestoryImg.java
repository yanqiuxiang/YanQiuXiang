package com.yqx.jurisdiction.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UploadDestoryImg
{
	private static class DestoryImg
	{
		public String imgFile;
		public long imgTime;

		public DestoryImg(String imgFile)
		{
			this.imgFile = imgFile;
			this.imgTime = System.currentTimeMillis();
		}
	}

	public static UploadDestoryImg mUploadDestoryImg = new UploadDestoryImg();
	private Object mLock = new Object();
	private List<DestoryImg> mDestoryImgList = new ArrayList<DestoryImg>();

	public static UploadDestoryImg getInstance()
	{
		return mUploadDestoryImg;
	}

	private UploadDestoryImg()
	{
	}

	public void addDestoryImg(String imgFile)
	{
		deleteTimeoutImgFile();
		synchronized (mLock)
		{
			mDestoryImgList.add(new DestoryImg(imgFile));
		}
	}

	public void removeDestoryImg(String imgFileList)
	{
		if(imgFileList==null) return;
		if(imgFileList.equals("")) return;
		String[] list= imgFileList.split(",");
		removeDestoryImg(list);
	}
	
	public void removeDestoryImg(String[] imgFileList)
	{
		synchronized (mLock)
		{
			for (int i = 0; i < imgFileList.length; i++)
			{
				for (int j = 0; j < mDestoryImgList.size(); j++)
				{
					if (mDestoryImgList.get(j).imgFile.equals(imgFileList[i]))
					{
						mDestoryImgList.remove(j);
						break;
					}
				}
			}
		}
	}

	protected void deleteTimeoutImgFile()
	{
		synchronized (mLock)
		{
			for (int i = 0; i < mDestoryImgList.size();)
			{
				if (System.currentTimeMillis() - mDestoryImgList.get(i).imgTime >= 1800 * 1000)
				{
					File file = new File(mDestoryImgList.get(i).imgFile);
					file.delete();
					mDestoryImgList.remove(i);
				}
				else
					i++;
			}
		}
	}
}
