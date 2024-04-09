import sequelize from "../db/connection";
import { Alarm } from "./alarm";
import { Contact } from "./contact";
import { Data } from "./data";
import { Greenhouse } from "./greenhouse";
import { Sensor } from "./sensor";
import { Threshold } from "./threshold";
import { DataTypes } from "sequelize";

export function initializeRelationships() {

    Alarm.belongsTo(Sensor, { foreignKey: 'id_sensor' });
    Sensor.hasMany(Alarm, { foreignKey: 'id_sensor' });


    Sensor.hasMany(Data, { foreignKey: 'id_sensor' });
    Data.belongsTo(Sensor, { foreignKey: 'id_sensor' });

    Sensor.belongsTo(Greenhouse, { foreignKey: 'id_Greenhouse' });
    Greenhouse.hasMany(Sensor, { foreignKey: 'id_Greenhouse' });

    const SensorThreshold = sequelize.define('sensor_threshold', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        }
    }, {
        timestamps: false
    });

    Sensor.belongsToMany(Threshold, { through: SensorThreshold });
    Threshold.belongsToMany(Sensor, { through: SensorThreshold });


    const GreenhouseContact = sequelize.define('greenhouse_contact', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        }
    }, {
        timestamps: false
    });

    Greenhouse.belongsToMany(Contact, { through: GreenhouseContact });
    Contact.belongsToMany(Greenhouse, { through: GreenhouseContact });
}