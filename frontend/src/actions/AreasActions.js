import axios from 'axios';

export const findAllAreas = (name) => {
    return axios.get('/v1/areas' + '?name=' + name)
        .then(response => response.data)
        .catch(error => alert(error))
}