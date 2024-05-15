import { DataTypes } from "sequelize";
import sequelize from "../db/connection";

export const Greenhouse = sequelize.define('Greenhouses', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false
    },
    location: {
        type: DataTypes.STRING,
        allowNull: false
    },
    description: {
        type: DataTypes.STRING
    }
}, {
    timestamps: false
});

