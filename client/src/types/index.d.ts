import VueI18n from 'vue-i18n'
import _Vue, { PluginObject, PluginFunction } from 'vue'

export interface RootState {
    version: string;
}

export interface WebApi<T> {
    [apiName: string] :T;
}

export interface DialogOptions {
    id?: string;
    resolve?: (p: any) => any;
    reject?: (p: any) => any;
    title?: string;
    text?: string;
    apply?: string;
    cancel?: string;
    confirm?: boolean;
    maxWidth?: number;
    $t?: (key: VueI18n.Path, values?: VueI18n.Values) => string;
    [key: string]: any;
}

export interface func {
    (v: any): any;
}

export interface LogLevel {
    debug(...args: any[]): void;
    info(...args: any[]): void;
    warn(...args: any[]): void;
    error(...args: any[]): void;
    fatal(...args: any[]): void;
}

export interface VueModules {
    [key: string]: PluginObject<any> | PluginFunction<any>;
}

export type Nullable<T> = T | null;
