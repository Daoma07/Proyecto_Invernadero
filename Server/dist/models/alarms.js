"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Alarm = void 0;
const sequelize_1 = require("sequelize");
const connection_1 = __importDefault(require("../db/connection"));
const sensor_1 = require("./sensor");
exports.Alarm = connection_1.default.define('alarms', {
    id: {
        type: sequelize_1.DataTypes.NUMBER,
        primaryKey: true,
        autoIncrement: true
    },
    exceeded_value: {
        type: sequelize_1.DataTypes.FLOAT,
        allowNull: false
    },
    date: {
        type: sequelize_1.DataTypes.DATE,
        allowNull: false
    },
    alarm_type: {
        type: sequelize_1.DataTypes.ENUM('BAJA', 'ALTA'),
        allowNull: false
    }
}, {
    timestamps: false
});
exports.Alarm.belongsTo(sensor_1.Sensor, { foreignKey: 'id_sensor' });
sensor_1.Sensor.hasMany(exports.Alarm, { foreignKey: 'id_sensor' });
