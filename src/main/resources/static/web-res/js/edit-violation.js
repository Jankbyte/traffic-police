const inputCarId = document.getElementById("inputCarId");
const inputMessage = document.getElementById("inputMessage");
const inputTime = document.getElementById("inputTime");
const checkBoxActive = document.getElementById("checkBoxActive");
const violationErrors = document.getElementById("violation-errors");

function onClickCreateViolation() {
    violationErrors.hidden = true;
    violationErrors.innerHTML = "";
    const url = "/api/violation/" + inputCarId.value;
    const xhr = new XMLHttpRequest();
    xhr.open("PUT", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("/car/" + inputCarId.value);
            } else {
                showViolationErrors(xhr.responseText);
            }
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(getViolationJSON());
}

function onClickUpdateViolation() {
    violationErrors.hidden = true;
    violationErrors.innerHTML = "";
    const url = "/api/violation/" + getIdByUrl();
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("/violation/" + getIdByUrl());
            } else {
                showViolationErrors(xhr.responseText);
            }
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(getViolationJSON());
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

function getViolationJSON() {
    return JSON.stringify(
        {
          message : inputMessage.value,
          time : inputTime.value,
          active : checkBoxActive.checked
        }
    );
}
