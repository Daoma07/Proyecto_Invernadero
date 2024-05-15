import { DataTypes } from "sequelize";
import sequelize from "../db/connection";


export const Threshold = sequelize.define('thresholds', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    min: {
        type: DataTypes.FLOAT,
        allowNull: false
    },
    max: {
        type: DataTypes.FLOAT,
        allowNull: false
    },
    condition: {
        type: DataTypes.ENUM('Primavera', 'Verano', 'Otoño', 'Invierno'),
        allowNull: false
    }
}, {
    timestamps: false
});



