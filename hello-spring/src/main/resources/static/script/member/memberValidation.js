function validateEmail(email) {
	let re =
		/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(String(email).toLowerCase());
};
function validatePwd(pwd) {
	let re =
	/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
		return re.test(String(pwd));
}
function pwdCheck(pwd1, pwd2) {
	return pwd1.value != pwd2.value ? (alert('패스워드가 일치하지 않습니다.'), pwd2.focus()) : '';
}

export {
	validateEmail,
	validatePwd,
	pwdCheck,
}