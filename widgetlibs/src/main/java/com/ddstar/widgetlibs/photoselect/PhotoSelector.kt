package com.ddstar.widgetlibs.photoselect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ddstar.widgetlibs.R
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig

/**
 * Created by DDStar on 2018/11/10.
 */
class PhotoSelector : RecyclerView {
    lateinit var mAdapter: PhotoAdapter
    var maxNum = 9

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PictureConfig.CHOOSE_REQUEST -> {
                //图片选择
                if (resultCode == Activity.RESULT_OK) {
                    // 图片、视频、音频选择结果回调
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    if (selectList != null && selectList.size > 0) {
                        ArrayList<String>().let {
                            for (item in selectList) {
                                var picPath = item.compressPath
                                if (TextUtils.isEmpty(picPath)) {
                                    picPath = item.path
                                }
                                it.add(picPath)
                            }
                            mAdapter.addDatas(it)
                        }
                    }
                }
            }
        }
    }

    fun init() {
        mAdapter = PhotoAdapter()
        layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        adapter = mAdapter
    }

    inner class PhotoAdapter : Adapter<VH> {

        constructor() : super() {
            mDatas.add("")
        }

        fun addDatas(datas: ArrayList<String>) {
            if (TextUtils.isEmpty(mDatas[mDatas.size - 1])) {
                mDatas.removeAt(mDatas.size - 1)
            }
            mDatas.addAll(datas)
            if (mDatas.size < maxNum) {
                mDatas.add("")
            }
            notifyDataSetChanged()
        }

        fun getLeftPhotoNums(): Int {
            if (mDatas.size == 1)
                return maxNum
            if (TextUtils.isEmpty(mDatas[mDatas.size - 1])) {
                return maxNum - mDatas.size + 1
            }
            return 0
        }

        fun getSelectedPhotoList(): ArrayList<String> {
            ArrayList<String>().let {
                for (item in mDatas) {
                    if (!TextUtils.isEmpty(item)) {
                        it.add(item)
                    }
                }
                return it
            }
        }

        var mDatas = ArrayList<String>()
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH {
            return VH(LayoutInflater.from(context).inflate(R.layout.layout_photo_item, p0, false))
        }

        override fun getItemCount(): Int {
            return mDatas.size
        }

        override fun onBindViewHolder(p0: VH, p1: Int) {
            val photoPath = mDatas[p1]
            val ivPhoto = p0.ivPhoto
            val btnDelete = p0.btnDelete
            btnDelete.visibility = View.VISIBLE
            if (TextUtils.isEmpty(photoPath)) {
                btnDelete.visibility = View.GONE
                ivPhoto.setImageResource(R.drawable.icon_default_photo)
            } else {
                Glide.with(context).load(photoPath).into(ivPhoto)
            }
            p0.ivPhoto.setOnClickListener {
                if (TextUtils.isEmpty(photoPath)) {
                    PicturesSelectorUtil.chooseMulitPhotos(context as Activity, getLeftPhotoNums())
                } else {
                    Toast.makeText(context, "看大图", Toast.LENGTH_SHORT).show()
                }
            }
            p0.btnDelete.setOnClickListener {
                deleteItem(p1)
            }
        }

        private fun deleteItem(p1: Int) {
            mDatas.removeAt(p1)
            if (!TextUtils.isEmpty(mDatas[mDatas.size - 1])) {
                mDatas.add("")
            }
            notifyDataSetChanged()
        }

    }

    inner class VH : ViewHolder {
        var ivPhoto: ImageView
        var btnDelete: View

        constructor(itemView: View) : super(itemView) {
            ivPhoto = itemView.findViewById(R.id.iv_photo)
            btnDelete = itemView.findViewById(R.id.rly_btn_delete)
        }
    }
}