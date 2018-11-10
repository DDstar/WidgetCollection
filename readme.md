#### 常用开发控件整理
* 1 地址选择器
* 2 时间选择 日期选择
* 3 图片视频选择
* 4 购物车数量添加与减少
* 5 购物车逻辑
* 6 网络请求状态展示
* 7 列表加载更多
* 8 网络请求相应（网络请求框架不纳入）
* 9 支付（微信 支付宝 余额 银联）
* 10  tag选取
* 11 图片选取

    使用方法

    1布局添加

    ```
        <com.ddstar.widgetlibs.photoselect.PhotoSelector
            android:id="@+id/photo_selector"
            android:background="@color/white" />
    ```

    2 界面里面监听页面回调

     ```
     
      override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        photoSelector.onActivityResult(requestCode, resultCode, data)
      }
      
    ```

    3 获取选中图片

    ```
    val selectedPhotoList = photoSelector.mAdapter.getSelectedPhotoList()
    
    ```
