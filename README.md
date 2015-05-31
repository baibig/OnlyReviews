# OnlyReviews
利用豆瓣API查询豆瓣影评以及豆瓣top250
这是我学习安卓网络开发的一个例子，包含以下技术：
- 用SAXParse来XML解析，重点是ContentHandler的处理，可以很方便的对XML数据进行解析
- 用Gson解析json数据，要注意的是自定义的对象并不需要和接受的json数据的格式完全匹配，Gson只解析匹配的部分，所以自定义对象成员变量时要格外注意是否匹配。
- 用Volley框架进行网络通信，包括以post方式请求json数据以及采用[NetworkImageView和ImageLoader](http://www.androidhive.info/2014/07/android-custom-listview-with-image-and-text-using-volley/)下载网络图片。
- v4包中的SwipRefreshLayout不具有上拉加载更多的功能，根据SwipRefreshLayout自定义了一个可以上拉加载更多的layout，不过还很简陋。
- 使用[PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip)和ViewPager实现了Indicator指示器的功能，不再需要使用特别麻烦的Actionbar.Tab，并且可以在Fragment内嵌Fragment，使用起来很方便。
- 使用Jsoup实现爬取正在热映和即将上映的电影的功能。
<div align="right">5月27</div>
- 实现点击电影跳转到电影评论列表页面和浏览评论功能，主要是各种接口及回调的运用，略显繁琐，准备学习一些安卓组件间通信的框架。
<div align="right">5月31</div>
