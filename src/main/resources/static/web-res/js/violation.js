function onClickDeleteViolation() {
    const url = "/api/violation/" + getIdByUrl();
    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            window.location.replace("/car");
        }
    }
    setCsrfHeader(xhr);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}
