import React, {Component} from "react";
import Typography from '@mui/material/Typography';
import {Accordion, AccordionDetails, AccordionSummary, Box, Button, Grid, TextField} from "@mui/material";
import {findAllAreas} from "../actions/AreasActions";

class Areas extends Component {

    state = {
        expanded: '',
        areas: []
    }

    async componentDidMount() {
        const data = await findAllAreas('');
        this.setState({areas: data});
    }


    handleChange = (panel) => (_event, newExpanded) => {
        this.setState({expanded: newExpanded ? panel : false})
    };

    render() {
        const {areas, expanded} = this.state;
        return (<Box sx={{p: 2, display: 'grid', gap: 2}}>
            {Array.from(areas).map((area, _index) => (
                <Accordion expanded={expanded === area.name} onChange={this.handleChange(area.name)}
                           key={area.id}>
                    <AccordionSummary aria-controls="panel1d-content" id="panel1d-header">
                        <Grid container alignContent="space-between">
                            <Grid item xs={10}>
                                <Typography>{area.name}</Typography>
                            </Grid>
                            <Grid item xs={2}>
                                <Button variant="contained" style={{width: "60px"}} size="small"
                                        color={area.hasDelivery ? "error" : "success"}>
                                    {area.hasDelivery ? 'Delete' : 'Add'}
                                </Button>
                            </Grid>
                        </Grid>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Grid container alignContent="space-between">
                            <Grid item style={{marginTop: "5px"}}>
                                <Typography>Base cost of delivery:</Typography>
                            </Grid>
                            <Grid item style={{marginLeft: "5px"}}>
                                <TextField id="base_cost" defaultValue={area.baseCharge} disabled={true}
                                           variant="standard"/>
                            </Grid>
                            <Grid item xs zeroMinWidth style={{marginTop: "5px"}}>
                                <Typography>$</Typography>
                            </Grid>
                            <Grid item xs={2}>
                                <Button type="submit" variant="contained" size="small" style={{width: "145px"}}>
                                    Add Markup
                                </Button>
                            </Grid>
                        </Grid>
                        {Array.from(area.extraCharges).map((charge, i) => (
                            <Grid container style={{marginTop: "15px"}} key={area.id + "_" + i}>
                                <Grid item>
                                    <TextField id={"min_weight_" + area.id + "_" + i}
                                               defaultValue={charge.minWeight} disabled={true}
                                               variant="standard"/>
                                </Grid>
                                <Grid item style={{marginTop: "5px"}}>
                                    <Typography>kg -</Typography>
                                </Grid>
                                <Grid item style={{marginLeft: "5px"}}>
                                    <TextField id={"max_weight_" + area.id + "_" + i}
                                               defaultValue={charge.maxWeight}
                                               disabled={true}
                                               variant="standard"/>
                                </Grid>
                                <Grid item xs zeroMinWidth style={{marginTop: "5px"}}>
                                    <Typography>kg</Typography>
                                </Grid>
                                <Grid item>
                                    <TextField id={"charge_" + area.id + "_" + i}
                                               defaultValue={charge.charge}
                                               disabled={true}
                                               variant="standard"/>
                                </Grid>
                                <Grid item xs zeroMinWidth style={{marginTop: "5px"}}>
                                    <Typography>$</Typography>
                                </Grid>
                                <Grid item xs={2}>
                                    <Button type="submit" variant="contained" size="small" style={{width: "145px"}}>
                                        Remove Markup
                                    </Button>
                                </Grid>
                            </Grid>
                        ))}
                    </AccordionDetails>
                </Accordion>))}
        </Box>);
    }
}

export default Areas;