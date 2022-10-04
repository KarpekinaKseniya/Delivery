import React, {Component} from "react";
import {Card, CardContent, CardMedia} from "@mui/material";
import Typography from "@mui/material/Typography";
import Image from "../sadness_not_found.png";

class NotFound extends Component {

    render() {
        return (
            <Card sx={{maxWidth: 300, marginLeft: "40%"}}>
                <CardContent sx={{flex: '1 0 auto'}}>
                    <Typography variant="h4">
                        Areas Not Found
                    </Typography>
                </CardContent>
                <CardMedia
                    component="img"
                    sx={{width: 300, height: 300}}
                    image={Image}
                    alt="Not Found"/>
            </Card>
        );
    }

}

export default NotFound;