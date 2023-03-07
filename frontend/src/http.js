import axios from "axios";


const http = axios.create({
    headers: {
        Accept: 'application/json',
        Content: 'application/json'
    }
});
    
http.interceptors.request.use( config => {
    const token = sessionStorage.getItem('token');
    
    if(token)
        config.headers['Authorization'] = 'Bearer ' + token;
    return config;
}, error => {
    Promise.reject(error)
});

export default http;