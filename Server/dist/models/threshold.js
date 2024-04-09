"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Threshold = void 0;
const sequelize_1 = require("sequelize");
const connection_1 = __importDefault(require("../db/connection"));
exports.Threshold = connection_1.default.define('thresholds', {
    id: {
        type: sequelize_1.DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    min: {
        type: sequelize_1.DataTypes.FLOAT,
        allowNull: false
    },
    max: {
        type: sequelize_1.DataTypes.FLOAT,
        allowNull: false
    },
    condition: {
        type: sequelize_1.DataTypes.ENUM('Primavera', 'Verano', 'Otoño', 'Invierno'),
        allowNull: false
    }
}, {
    timestamps: false
});
