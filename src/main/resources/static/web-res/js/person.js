function onClickDeletePerson() {
    const url = "/api/person/" + getIdByUrl();
    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            window.location.replace("/person");
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}