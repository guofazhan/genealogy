var stompClient = null;
$(function () {
    connect();
});

function connect() {
    var sock = new SockJS("/endpointChat");
    var stomp = Stomp.over(sock);
    stomp.connect('guest', 'guest', function (frame) {

        /**  订阅了/user/queue/notifications 发送的消息,这里雨在控制器的 convertAndSendToUser 定义的地址保持一致, 
         *  这里多用了一个/user,并且这个user 是必须的,使用user 才会发送消息到指定的用户。 
         *  */
        stomp.subscribe("/user/queue/notifications", handleNotification);
        stomp.subscribe('/topic/getResponse', function (response) { //订阅/topic/getResponse 目标发送的消息。这个是在控制器的@SendTo中定义的。
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "progressBar": true,
                "positionClass": "toast-bottom-right",
                "onclick": null,
                "showDuration": "400",
                "hideDuration": "1000",
                "timeOut": "7000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr.info(JSON.parse(response.body).responseMessage);
        });
    });

    function handleNotification(message) {
        wrapper.notify();
        toastr.info(message.body);
    }
}

var wrapper = new Vue({
    el: '#wrapper',
    data: {
        total: '',
        rows: '',
    },
    methods: {
        notify: function () {
            $.getJSON('/oa/notify/message', function (r) {
                wrapper.total = r.total;
                wrapper.rows = r.rows;
            });
        },
        personal: function () {
            layer.open({
                type: 2,
                title: '个人设置',
                maxmin: true,
                shadeClose: false,
                area: ['800px', '600px'],
                content: '/sys/user/personal'
            });
        }
    },
    created: function () {
        this.notify()
    }
})