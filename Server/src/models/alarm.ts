import { DataTypes } from "sequelize";
import sequelize from "../db/connection";


export const Alarm = sequelize.define('alarms', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    exceeded_value: {
        type: DataTypes.FLOAT,
        allowNull: false
    },
    date: {
        type: DataTypes.DATE,
        allowNull: false
    },
    alarm_type: {
        type: DataTypes.ENUM('BAJA', 'ALTA'),
        allowNull: false
    },
    id_sensor: {
        type: DataTypes.INTEGER,
        allowNull: true
    }
}, {
    timestamps: false
});


