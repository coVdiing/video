import Swal from "sweetalert2";

const Toast = Swal.mixin({
    toast: true,
    position: 'top-middle',
    showConfirmButton: false,
    timer: 500,
    timerProgressBar: true,
    didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
});


function alertSuccess(message) {
    Toast.fire({
        icon: 'success',
        title: message
    })
}

function alertError(message) {
    Toast.fire({
        icon: 'error',
        title: message
    })
}

function alertWarn(message) {
    Toast.fire({
        icon: 'warning',
        title: message
    })
}


export {alertSuccess,alertError,alertWarn}