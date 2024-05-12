import { DataTypes } from "sequelize";
import sequelize from "../db/connection";


export const Data = sequelize.define('datas', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    type: {
        type: DataTypes.STRING,
        allowNull: false
    },
    magnitude: {
        type: DataTypes.STRING,
        allowNull: false
    },
    date: {
        type: DataTypes.DATE,
        allowNull: false
    },
    value: {
        type: DataTypes.FLOAT,
        allowNull: false
    }
}, {
    timestamps: false
});


