
import { Injectable } from "@angular/core";
import { MatSnackBarConfig, MatSnackBar } from "@angular/material";
import { InfoSnackbarComponent, SuccessSnackbarComponent, ErrorSnackbarComponent } from "../app.component";
@Injectable()
export class SnackService {
	constructor(public snackBar: MatSnackBar) {

    }
    showSnack(message: string, type: string ='Info', duration: number = 3000) {
        //debugger;
        let config = new MatSnackBarConfig();
        config.duration = duration;// this.autoHide;
        
        config.data = message;
        if (type == "Error")
        {
            config.extraClasses = ['snackError'];
            return this.snackBar.openFromComponent(ErrorSnackbarComponent, config);
        }
        else if (type == "Success")
			{
				config.extraClasses = ['snackSuccess'];
                return this.snackBar.openFromComponent(SuccessSnackbarComponent, config);
        }
        else
			{
				 config.extraClasses = ['snackInfo'];
                return this.snackBar.openFromComponent(InfoSnackbarComponent, config);
        }
	}
}