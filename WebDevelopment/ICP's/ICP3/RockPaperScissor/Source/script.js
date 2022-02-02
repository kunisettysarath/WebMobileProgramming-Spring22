// variables to store win streak
var userWins = 0;
var computerWins = 0;

// adding the eventlisteners for the signs
for (var v = 0; v < 3; v++) {
  document
    .querySelectorAll(".game-inputs")
    [v].addEventListener("click", function (event) {
      findWinner(this.getAttribute("id"));
      document.querySelector(".game-start-info").innerHTML =
        "You are now playing against computer";
    });
}

// this method will find the winner and will display the user streaks
function findWinner(userInput) {
  var computerInput = getcomputerInput();
  console.log("user input " + userInput);
  console.log("computer input " + computerInput);
  document.querySelector(".user-input").innerHTML = "You: " + userInput;
  document.querySelector(".computer-input").innerHTML =
    "Computer: " + computerInput;
  if (userInput == computerInput) {
    document.querySelector(".game-info").innerHTML = "It's a Draw!!";
    // location.reload();
  } else {
    if (
      (userInput == "rock" && computerInput == "paper") ||
      (userInput == "paper" && computerInput == "scissor") ||
      (userInput == "scissor" && computerInput == "rock")
    ) {
      document.querySelector(".game-info").innerHTML = "Computer Wins!!";
      userWins++;
    } else {
      document.querySelector(".game-info").innerHTML = "You WON!!";
      computerWins++;
    }
    document.querySelector(".score-status").innerHTML =
      userWins + "     :     " + computerWins;
  }
}

// random number generator for computer choice
function getcomputerInput() {
  const num = Math.floor(Math.random() * 3) + 1;
  if (num === 1) return "rock";
  else if (num === 2) return "paper";
  else return "scissor";
}
