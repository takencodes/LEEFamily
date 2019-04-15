var timer;
var remind_Type = false;
// var userId = '${userOfLogin.company.id}';
if(userId != null && userId != '' && userId != 'undefined') {
    //登录成功后获取所有提示信息
    getAllRemind();
    timer = setTimeout("connectWebSocket()",6000);
}
function connectWebSocket() {
    var socket = new SockJS('/endpointChat'); //连接SockJS的endpoint名称为"endpointChat"
    var stompClient = Stomp.over(socket);//使用STMOP子协议的WebSocket客户端
    stompClient.connect({}, function (frame) {//连接WebSocket服务端
        console.log('Connected:' + frame);
        //通过stompClient.subscribe订阅/topic/getResponse 目标(destination)发送的消息,这个是在控制器的@SentTo中定义的
        stompClient.subscribe('/topic/' + userId, function (response) {
            // document.getElementById("playSound").play();
            var remind = JSON.parse(response.body);
            var remindId = remind.id;
            var content;
            var remindType = remind.remindType;
            if(remindType == 'ORDER'){
                document.getElementById("newOrderMessage").play();
                content = '<h4 style="color: #ff0907;text-align: center;margin-top: 18px;margin-bottom: 25px;">您有新的线上订单，请及时处理</h4>';
            }
            else if(remindType == 'VOUCHER'){
                document.getElementById("newMessage").play();
                content = '<h4 style="color: #ff0907;text-align: center;margin-top: 18px;margin-bottom: 25px;">隔壁仓库给您的终端用户派发了优惠券</h4>';
            }
            else if(remindType == 'NOTICE'){
                document.getElementById("newMessage").play();
                var remindContent = remind.responseMessage;
                remind_Type = true;
                content ='<a href="javascript:void(0)" onclick="toRemindDetail(\''+remindId+'\',\''+remindContent+'\')">';
                content += '<h4 style="color: #ff0907;text-align: center;margin-top: 18px;margin-bottom: 25px;">隔壁仓库发布了最新公告，点击查看</h4>';
                content +='</a>';
            }
            content += '<div style="border-bottom: 1px solid #e9e9e9 "></div>';
            content += '<a href="javascript:void(0)" style="text-align: right;margin-left: 10px;" onclick="closeAll()">忽略全部</a>';
            showRemind(remindId,content,remindType);
        });
    });
    clearTimeout(timer);
}

function showRemind(remindId,content,remindType) {
    layer.open({
        id: remindId,
        type: 1,
        title: "新消息",
        shade: false,
        area: ['210px', '145px'],
        offset: 'rb', //右下角弹出
        //time: 2000, //2秒后自动关闭
        anim: 0,
        content: content,
        //关闭提醒时删除redis提醒数据
        end: function () {
            $.ajax({
                url: "remind/delRemind.do",
                data:{remindId:remindId},
                type: "POST",
                success: function (resp) {
                    if (resp) {
                        if(remindType == 'ORDER'){
                            document.getElementById("newOrderMessage").pause();
                        }else if(remindType == 'NOTICE'){
                            document.getElementById("newMessage").pause();
                        }else if(remindType == 'VOUCHER'){
                            document.getElementById("newMessage").pause();
                        }
                    }
                }
            });
        }
    });
}

function toRemindDetail(remindId,remindContent) {
    console.log(remindId);
    $.ajax({
        url: "remind/delRemind.do",
        data:{remindId:remindId},
        type: "POST",
        success: function (resp) {
            if (resp) {
                if(remind_Type){
                    document.getElementById("newMessage").pause();
                }else {
                    document.getElementById("newOrderMessage").pause();
                }
                location.href ="/announcement/detail/"+remindContent+".do";
            }
        }
    });
    // closeRemind(remindId,remindType);

}

//获取当前合伙人下所有提醒信息
function getAllRemind() {
    $.ajax({
        url: "/remind/getAllRemind.do",
        type: "POST",
        success: function (resp) {
            console.log(resp);
            if(resp != null && resp != ''){
                console.log('总共有'+resp.remindRos.length+'条提醒信息');
                var remindId,content;
                for(var i=0;i<resp.remindRos.length;i++){
                    var remind = resp.remindRos[i];
                    remindId = remind.id;
                    var remindType = remind.remindType;
                    if(remindType == 'ORDER'){
                        document.getElementById("newOrderMessage").play();
                        content = '<h4 style="color: #ff0907;text-align: center;margin-top: 18px;margin-bottom: 25px;">您有新的线上订单，请及时处理</h4>';
                    }
                    else if(remindType == 'VOUCHER'){
                        document.getElementById("newMessage").play();
                        content = '<h4 style="color: #ff0907;text-align: center;margin-top: 18px;margin-bottom: 25px;">隔壁仓库给您的终端用户派发了优惠券</h4>';
                    }
                    else if(remindType == 'NOTICE'){
                        document.getElementById("newMessage").play();
                        var remindContent = remind.remindContent;
                        remind_Type = true;
                        content ='<a href="javascript:void(0)" onclick="toRemindDetail(\''+remindId+'\',\''+remindContent+'\')">';
                        content += '<h4 style="color: #ff0907;text-align: center;margin-top: 18px;margin-bottom: 25px;">隔壁仓库发布了最新公告，点击查看</h4>';
                        content +='</a>';
                    }
                    content += '<div style="border-bottom: 1px solid #e9e9e9 "></div>';
                    content += '<a href="javascript:void(0)" style="text-align: right;margin-left: 10px;" onclick="closeAll()">忽略全部</a>';
                    showRemind(remindId,content,remindType);
                }
            }
        }
    });
}

function closeAll(){
    $.ajax({
        url: "remind/closeAll.do",
        type: "POST",
        success: function (resp) {
            if (resp) {
                layer.closeAll();
            }
        }
    });
}
