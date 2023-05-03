<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>
<script>

  let register_form = {

    init:function(){
      $('#register_btn').addClass('disabled');

      $('#register_btn').click(function(){
        register_form.send();
      });

      $('#name').keyup(function(){
        var id = $('#id').val();
        var pwd = $('#pwd').val();
        var lev = $('#lev').val();
        if(id != '' && pwd != '' && lev != ''){
          $('#register_btn').removeClass('disabled');
        }

      })
      $('#id').keyup(function(){

        var txt_id = $(this).val();
        if(txt_id.length<=3){
          return;
        }
        $.ajax({
          url:'/checkid',
          data:{'id':txt_id},
          //success일때는 콤마, 세미콜론 둘 다 없다 주의하기!
          success:function(result){
            if(result==0){
              $('#check_id').text('사용가능합니다.');
              $('#pwd').focus();
            }else{
              $('#check_id').text('사용불가능합니다.');

            }
          }
        });
      });
    },
    send:function(){
      var id = $('#id').val();
      var pwd = $('#pwd').val();
      var lev = $('#lev').val();
      if(id.length<=3){
        $('#check_id').text('4자리 이상이어야 합니다.');
        $('#id').focus();
        return;
      }
      if(pwd == ''){
        $('#pwd').focus();
        return;
      }
      if(lev == ''){
        $('#lev').focus();
        return;
      }

      $('#register_form').attr({
        'action':'/registerimpl',
        'method':'post'
      });
      $('#register_form').submit();
    }
  };
  $(function(){
    register_form.init();
  });


</script>

<div class="container">
  <div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
      <!-- Nested Row within Card Body -->
      <div class="row">
        <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
        <div class="col-lg-7">
          <div class="p-5">
            <div class="text-center">
              <h1 class="h4 text-gray-900 mb-4">Register</h1>
            </div>
              <form class="user" id="register_form">

                <div class="form-group">
                  <input type="text" class="form-control form-control-user" id="id" name="id"
                         placeholder="Enter ID">
                </div>


                <div class="form-group">
                  <input type="password" class="form-control form-control-user" id="pwd" name="pwd"
                         placeholder="Enter Password">
                </div>

                <div class="form-group">
                  <input type="text" class="form-control form-control-user" id="lev" name="lev"
                         placeholder="Enter Level">
                </div>

                <button id="register_btn" type="button" class="btn btn-primary btn-user btn-block">Register</button>

                <hr>
              </form>
            <hr>

          </div>
        </div>
      </div>
    </div>
  </div>

</div>

