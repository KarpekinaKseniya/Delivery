import axios from 'axios';
import {NotificationManager} from "react-notifications";

export const findAllAreas = (name) => {
    return axios.get('/v1/areas' + '?name=' + name)
        .then(response => response.data)
        .catch(error => NotificationManager.error(error, 'Error'));
}

export const updateArea = (id, area) => {
    return axios.patch(`/v1/areas/${id}`, area)
        .then(response => response.status)
        .catch(error => NotificationManager.error(error, 'Error'))
}