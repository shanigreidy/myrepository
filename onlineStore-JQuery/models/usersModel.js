module.exports = function(){
    var users = [];
    var loggedInUser = null;

    function getAllUsers()
    {
        return users;
    }

    function addUser(userDataObj)
    {
       users.push(userDataObj);
       loggedInUser = userDataObj.name;
    }

    function getCurrentUser()
    {
        return loggedInUser;
    }

    function loginUser(userName)
    {
        loggedInUser = userName;
    }

    function logOut()
    {
        loggedInUser = null;
    }


    return{
      addUser: addUser,
      getAllUsers: getAllUsers,
      getCurrentUser: getCurrentUser,
      loginUser: loginUser,
      logOut: logOut
    };

}();