import axios from 'axios';

export const findAllAreas = (name) => {
    return axios.get('/v1/areas' + '?name=' + name)
        .then(response => response.data)
        .catch(error => alert(error))
}

export const updateArea = (id, area) => {
    return axios.patch(`/v1/areas/${id}`, area)
        .then(response => response.status)
        .catch(error => alert(error))
}