/*$('#login_form').validate({
  onkeyup: false,
  submitHandler: function () {
      console.log($('#id').val())
    return true;
  },
  rules: {
    id: {
      required: true,
      minlength: 1
    },
    password: {
      required: true,
      minlength: 1,
      remote: {
        url: '/weblogin',
        type: 'post',
        data: {
          id: function () {
              console.log($('#id').val())
            return $('#id').val();
          }
        },
        dataFilter: function (data) {
          var data = JSON.parse(data);
            console.log(data);
          if (data.success) {
            return true
          } else {
            return "\"" + data.msg + "\"";
          }
        }
      }
    }
  }
});*/