let newR;
window.addEventListener("DOMContentLoaded", () => {
    let xButtons = document.querySelectorAll('.x-button');
    xButtons.forEach(function (button) {
        button.addEventListener('click', event => {
            xButtons.forEach(function (button) {
                button.classList.remove('active');
            });
            event.target.classList.add('active');
        });
    });
})