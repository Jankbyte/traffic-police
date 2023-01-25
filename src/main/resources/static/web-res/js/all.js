function setCsrfHeader(ajax) {
    let token = document.querySelector(
        'meta[name="_csrf"]').content;
    let header = document.querySelector(
        'meta[name="_csrf_header"]').content;
    ajax.setRequestHeader(header, token);
}

function onClickLogout(event) {
    event.preventDefault();
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/logout", true);
    setCsrfHeader(xhr);
    xhr.send();
    window.location.replace("/login");
}

function getIdByUrl() {
    let url = window.location.href;
    let pathes = url.split("/");
    return pathes[pathes.length - 1];
}
