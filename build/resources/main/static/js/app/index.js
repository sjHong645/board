var main = {
    init : function () {
        var _this = this;

        // javascript에서
        // # = id를 의미한다.

        // 즉, id = btn-save에서 click이라는 event가 발생했을 때
        // 해당 파일의 save 함수가 작동한다.
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function() {
            _this.update();
        });

        $('#btn-delete').on('click', function() {
            _this.delete();
        })

    },
    save : function () {

        // 이게 PostsApiController에 있는
        // save의 매개변수에 전달될 body 부분인가 보다.
        // 이 body 부분이라는 데이터가 전달되도록 하기 위해서 별도의 dto를 만든 거다.
        var data = {
            title: $('#title').val(), // id = title에 입력한 내용
            author: $('#author').val(), // id = author에 입력한 내용
            content: $('#content').val() // id = content에 입력한 내용
        };

        // 내가 들었던 그 AJAX를 사용했다.
        // 앞서 save는 postMapping 방식으로 진행
        // @PostMapping("/api/v1/posts")을 통해 해당 url에서 동작하도록 함
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();