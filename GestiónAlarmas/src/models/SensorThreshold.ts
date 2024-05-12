import { DataTypes, Model, Optional } from "sequelize";
import sequelize from "../db/connection";

export const SensorThreshold = sequelize.define('sensor_threshold', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    }
}, {
    timestamps: false
});