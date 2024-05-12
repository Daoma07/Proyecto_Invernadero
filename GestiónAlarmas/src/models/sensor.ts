
import { DataTypes, Model, Optional } from "sequelize";
import sequelize from "../db/connection";


export const Sensor = sequelize.define('sensors', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    protocol: {
        type: DataTypes.STRING,
        allowNull: true
    },
    serie: {
        type: DataTypes.STRING,
        allowNull: false
    },
    location: {
        type: DataTypes.STRING,
        allowNull: true
    },
    gateway: {
        type: DataTypes.STRING,
        allowNull: false
    }
}, {
    timestamps: false
});


interface SensorAttributes {
    id: number;
    protocol: string;
    serie: string;
    location: string;
    gateway: string;
}

interface SensorCreationAttributes extends Optional<SensorAttributes, 'id'> { }
export type SensorModel = Model<SensorAttributes, SensorCreationAttributes>;
