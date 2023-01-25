const inputName = document.getElementById("inputName");
const inputPassword = document.getElementById("inputPassword");
const selectRole = document.getElementById("select-role");
const checkBoxActive = document.getElementById("checkBoxActive");
const violationErrors = document.getElementById("violation-errors");

function onClickCreateUser() {
    violationErrors.hidden = true;
    violationErrors.innerHTML = "";
    const url = "/api/user";
    const xhr = new XMLHttpRequest();
    xhr.open("PUT", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("/user");
            } else {
                showViolationErrors(xhr.responseText);
            }
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(getUserJSON());
}

function onClickUpdateUser() {
    violationErrors.hidden = true;
    violationErrors.innerHTML = "";
    const xhr = new XMLHttpRequest();
    const url = "/api/user/" + getIdByUrl();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("/user/" + getIdByUrl());
            } else {
                showViolationErrors(xhr.responseText);
            }
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(getUserJSON());
}

function showViolationErrors(responseText) {
    const exception = JSON.parse(responseText).exception;
    const details = exception.details;
    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    cardBody.innerHTML = `<h5 class="card-title">${exception.message}</h5>`
    for (let i = 0; i < details.length; i++) {
        const error = details[i];
        cardBody.insertAdjacentHTML("beforeend",
            `<p class="card-text">${error}</p>`);
    }
    violationErrors.appendChild(cardBody);
    violationErrors.hidden = false;
}

function getUserJSON() {
    return JSON.stringify(
        {
          name : inputName.value,
          password : inputPassword != undefined ? inputPassword.value : null,
          enabled : checkBoxActive.checked,
          role : {
            name : selectRole.options[selectRole.selectedIndex].text
          }
        }
    );
}