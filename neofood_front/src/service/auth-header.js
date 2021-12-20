export default function authHeader(){
    let user = JSON.parse(localStorage.getItem('user'));

    if(user && user.jwtToken) {
        return {Authorization: user.jwtToken};
    } else {
        return {};
    }
}