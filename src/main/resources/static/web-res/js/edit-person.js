const inputFirstName = document.getElementById("inputFirstName");
const inputLastName = document.getElementById("inputLastName");
const inputPatronymic = document.getElementById("inputPatronymic");
const inputBirthDate = document.getElementById("inputBirthDate");
const inputCity = document.getElementById("inputCity");
const inputStreet = document.getElementById("inputStreet");
const inputHouse = document.getElementById("inputHouse");
const inputFlat = document.getElementById("inputFlat");
const inputPhoneNumber = document.getElementById("inputPhoneNumber");
const inputEmail = document.getElementById("inputEmail");
const violationErrors = document.getElementById("violation-errors");

function onClickCreatePerson() {
    violationErrors.hidden = true;
    violationErrors.innerHTML = "";
    const url = "/api/person";
    const xhr = new XMLHttpRequest();
    xhr.open("PUT", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("/person");
            } else {
                showViolationErrors(xhr.responseText);
            }
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(getPersonJSON());
}

function onClickUpdatePerson() {
    violationErrors.hidden = true;
    violationErrors.innerHTML = "";
    const url = "/api/person/" + getIdByUrl();
    const xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("/person/" + getIdByUrl());
            } else {
                showViolationErrors(xhr.responseText);
            }
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(getPersonJSON());
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

function getPersonJSON() {
    return JSON.stringify(
        {
          first_name : inputFirstName.value,
          last_name : inputLastName.value,
          patronymic : inputPatronymic.value,
          birth_date : inputBirthDate.value,
          address : {
            city : inputCity.value,
            street : inputStreet.value,
            house : inputHouse.value,
            flat : inputFlat.value
          },
          contact : {
            phone_number : inputPhoneNumber.value,
            email : inputEmail.value == "" ? null : inputEmail.value
          }
        }
    );
}