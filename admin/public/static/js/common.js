function showCharge(charge) {
    if (charge === 'f' || charge === 'F') {
        return CHARGE[1].value;
    } else {
        return CHARGE[0].value;
    }
}

function showCourseLevel(level) {
    if (level === '1') {
        return COURSE_LEVEL[0].value;
    } else if (level === '2') {
        return COURSE_LEVEL[1].value;
    } else if (level === '3') {
        return COURSE_LEVEL[2].value;
    }
}

function showCourseStatus(status) {
    if (status === 'P') {
        return COURSE_STATUS[0].value;
    } else {
        return COURSE_STATUS[1].value;
    }
}
export {showCharge,showCourseLevel,showCourseStatus}