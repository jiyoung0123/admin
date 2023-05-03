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
  let login_form = {

    init:function(){
      $('#login_btn').click(function(){
        login_form.send();
      });
    },
    send:function(){
      $('#login_form').attr({
        'action':'/loginimpl',
        'method':'post'
      });
      $('#login_form').submit();
    }
  };

  $(function(){
    login_form.init();
  });

</script>
<div class="container">

  <!-- Outer Row -->
  <div class="row justify-content-center">

    <div class="col-xl-10 col-lg-12 col-md-9">

      <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
          <!-- Nested Row within Card Body -->
          <div class="row">
            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
            <div class="col-lg-6">
              <div class="p-5">
                <div class="text-center">
                  <h1 class="h4 text-gray-900 mb-4">LOGIN</h1>
                </div>
                <form class="user" id="login_form">
                  <div class="form-group">
                    <input type="text" class="form-control form-control-user"
                           id="id" name="id"
                           placeholder="Enter ID...">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-user"
                           id="pwd" placeholder="Password" name="pwd">
                  </div>
                  <button id="login_btn" type="button" class="btn btn-primary btn-user btn-block">Login</button>
                  <hr>

                </form>
                <hr>

                <div class="text-center">
                  <a class="small" href="/register">Register</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>

</div>


