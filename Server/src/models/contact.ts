import { DataTypes } from "sequelize";
import sequelize from "../db/connection";


export const Contact = sequelize.define('contacts', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
        validate: {
            isEmail: true
        }
    },
    phone_number: {
        type: DataTypes.STRING,
        allowNull: false,
        validate: {
            is: /^\+[1-9]\d{1,14}$/
        }
    }
}, {
    timestamps: false
});





