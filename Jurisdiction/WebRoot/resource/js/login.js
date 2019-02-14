$(function(){
	//得到焦点
	$("#password").focus(function(){
		$("#left_hand").animate({
			left: "150",
			top: " -38"
		},{step: function(){
			if(parseInt($("#left_hand").css("left"))>140){
				$("#left_hand").attr("class","left_hand");
			}
		}}, 2000);
		$("#right_hand").animate({
			right: "-64",
			top: "-38px"
		},{step: function(){
			if(parseInt($("#right_hand").css("right"))> -70){
				$("#right_hand").attr("class","right_hand");
			}
		}}, 2000);
	});
	//失去焦点
	$("#password").blur(function(){
		$("#left_hand").attr("class","initial_left_hand");
		$("#left_hand").attr("style","left:100px;top:-12px;");
		$("#right_hand").attr("class","initial_right_hand");
		$("#right_hand").attr("style","right:-112px;top:-12px");
		});
});



function changeImg(){
    var imgSrc = $("#imgObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
 }
  //时间戳   
  //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
  function chgUrl(url){
    var timestamp = (new Date()).valueOf();
    var lastIndex = url.lastIndexOf("code");
   
    url = url.substring(0, 27);
  
    if((url.indexOf("&") >= 0)) {
      url = url + "×tamp=" + timestamp;
    }else{
      url = url + "?timestamp=" + timestamp;
    }
    

    return url;
  }