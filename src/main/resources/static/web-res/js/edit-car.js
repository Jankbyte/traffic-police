const inputBrand = document.getElementById("inputBrand");
const inputModel = document.getElementById("inputModel");
const inputColor = document.getElementById("inputColor");
const inputNumber = document.getElementById("inputNumber");
const inputCreated = document.getElementById("inputCreated");
const inputOwnerId = document.getElementById("inputOwnerId");
const violationErrors = document.getElementById("violation-errors");

function onClickCreateCar() {
    violationErrors.hidden = true;
    violationErrors.innerHTML = "";
    const url = "/api/car";
    const xhr = new XMLHttpRequest();
    xhr.open("PUT", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("/car");
            } else {
                showViolationErrors(xhr.responseText);
            }
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(getCarJSON());
}

function onClickUpdateCar() {
    violationErrors.hidden = true;
    violationErrors.innerHTML = "";
    const url = "/api/car/" + getIdByUrl();
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("/car/" + getIdByUrl());
            } else {
                showViolationErrors(xhr.responseText);
            }
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(getCarJSON());
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

function getCarJSON() {
    return JSON.stringify(
        {
          brand : inputBrand.value,
          model : inputModel.value,
          color : inputColor.value,
          number : inputNumber.value,
          created : inputCreated.value,
          owner : {
            "id" : inputOwnerId.value == "" ? null : inputOwnerId.value
          }
        }
    );
}