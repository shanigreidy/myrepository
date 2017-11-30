$(document).ready(function () {
    $("#loginForm").on("submit", loginUser);

    function loginUser(event) 
    {
        event.preventDefault();
        var userName = $("#login_user_email").val();
        var userPassword = $("#login_user_password").val();
  
        $.get('/users/',function(data){
            if(data)
            {
                 if (isUserExists(userName, data)) 
                 {
                     if (isPasswordMatchToUserPassword(userName, userPassword, data)) 
                     {
                         var userLoginData = {
                                     name: userName,
                                     password: userPassword  
                                 };

                         $.post("/users/login", userLoginData);
                         window.location= "/";
                     }
                     else
                     {
                        alert('password not match to user');
                     }
                 }
                 else
                 {
                    alert('user name not exists');
                 }

            }

        });
    }

    function isUserExists (userName, usersArr)
    {
        for(var i=0; i<usersArr.length; i++)
        {
            if(usersArr[i].name === userName)
            {
                return true;
            }
        }

        return false;

    }

    function isPasswordMatchToUserPassword (userName, userPassword, usersArr)
    {
        for(var i=0; i<usersArr.length; i++)
        {
            if(usersArr[i].name === userName && usersArr[i].password === userPassword)
            {
                return true;
            }
        }

        return false;
    }
});