import Swal from "sweetalert2";


function
showConfirm(message, callback) {
    Swal.fire({
        title: '确认删除',
        text: message,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '确认删除',
        cancelButtonText: '取消'
    }).then((result) => {
        if (result.value) {
            if (callback) {
                callback();
            }
        }
    })
}

export {showConfirm}