function getGithubInfo(user) {
  var req = new XMLHttpRequest();
  var res;
  req.open("Get", "https://api.github.com/users/" + user, true);
  req.onload = function () {
    if (req.status == 200) showUser(JSON.parse(req.responseText));
    else noSuchUser(username);
  };
  req.send();
}

function showUser(user) {
  console.log(user);
  $("#profile > h2").html("User Name - " + user.login);
  var userInfo;
  userInfo =
    "<img src='" +
    user.avatar_url +
    "' alt='users profile picture' style='height:200px;'>";
  userInfo = userInfo + "<p> user ID - " + user.id + "</p>";
  userInfo =
    userInfo + "<a href='" + user.html_url + "'>Click to view user account</a>";
  $(".avatar").html(userInfo);
}

function noSuchUser(username) {
  $("#profile > h2").html("Unable to find the user - '" + username + "'");
}

$(document).ready(function () {
  $(document).on("keypress", "#username", function (e) {
    if (e.which == 13) {
      username = $(this).val();
      $(this).val("");
      getGithubInfo(username);
    }
  });
});
